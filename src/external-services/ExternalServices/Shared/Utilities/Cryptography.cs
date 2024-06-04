using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;

namespace Shared.Utilities
{
    public static class Cryptography
    {
        private static readonly Aes _aes = Aes.Create();

        public static byte[] EncryptStringToBytes_Aes(string plainText, byte[]? Key = null, byte[]? IV = null, Aes? aes = null)
        {
            Key = Key is null ? _aes.Key : null;
            IV = IV is null ? _aes.IV : null;
            aes = aes is null ? _aes : null;

            // Check arguments.
            if (plainText == null || plainText.Length <= 0)
                throw new ArgumentNullException("Null or empty crypt data");
            if (Key == null || Key.Length <= 0)
                throw new ArgumentNullException("Null or empty key");
            if (IV == null || IV.Length <= 0)
                throw new ArgumentNullException("Null or empty IV");
            byte[] encrypted;

            // Create an Aes object
            // with the specified key and IV.
            {
                aes.Key = Key;
                aes.IV = IV;

                // Create an encryptor to perform the stream transform.
                ICryptoTransform encryptor = aes.CreateEncryptor(aes.Key, aes.IV);

                // Create the streams used for encryption.
                using (MemoryStream msEncrypt = new MemoryStream())
                {
                    using (CryptoStream csEncrypt = new CryptoStream(msEncrypt, encryptor, CryptoStreamMode.Write))
                    {
                        using (StreamWriter swEncrypt = new StreamWriter(csEncrypt))
                        {
                            //Write all data to the stream.
                            swEncrypt.Write(plainText);
                        }
                        encrypted = msEncrypt.ToArray();
                    }
                }
            }

            // Return the encrypted bytes from the memory stream.
            return encrypted;
        }

        public static string DecryptStringFromBytes_Aes(byte[] cipherText, byte[]? Key = null, byte[]? IV = null, Aes? aes = null)
        {
            Key = Key is null ? _aes.Key : null;
            IV = IV is null ? _aes.IV : null;
            aes = aes is null ? _aes : null;

            // Check arguments.
            if (cipherText == null || cipherText.Length <= 0)
                throw new ArgumentNullException("Null or empty cipher text");
            if (Key == null || Key.Length <= 0)
                throw new ArgumentNullException("Null or empty key");
            if (IV == null || IV.Length <= 0)
                throw new ArgumentNullException("Null or empty IV");

            // Declare the string used to hold
            // the decrypted text.
            string plaintext = null;

            // Create an Aes object
            // with the specified key and IV.
            {
                aes.Key = Key;
                aes.IV = IV;

                // Create a decryptor to perform the stream transform.
                ICryptoTransform decryptor = aes.CreateDecryptor(aes.Key, aes.IV);

                // Create the streams used for decryption.
                using (MemoryStream msDecrypt = new MemoryStream(cipherText))
                {
                    using (CryptoStream csDecrypt = new CryptoStream(msDecrypt, decryptor, CryptoStreamMode.Read))
                    {
                        using (StreamReader srDecrypt = new StreamReader(csDecrypt))
                        {

                            // Read the decrypted bytes from the decrypting stream
                            // and place them in a string.
                            plaintext = srDecrypt.ReadToEnd();
                        }
                    }
                }
            }

            return plaintext;
        }
    }
}
